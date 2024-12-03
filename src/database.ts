import * as mongodb from 'mongodb';
import { GameModel, PlayerModel } from './types';
import { schemas } from './schemas';

export const collections: {
  player?: mongodb.Collection<PlayerModel>;
  game?: mongodb.Collection<GameModel>;
} = {};

export async function connectToDatabase(uri: string) {
  const client = new mongodb.MongoClient(uri);
  await client.connect();

  const db = client.db('timeRush');
  await applySchemaValidation(db);

  collections.player = db.collection<PlayerModel>('player');
  collections.game = db.collection<GameModel>('game');
}

async function applySchemaValidation(db: mongodb.Db) {
  for (const schema of schemas) {
    // Try applying the modification to the collection, if the collection doesn't exist, create it
    await db
      .command({
        collMod: schema.collection,
        validator: schema.json,
      })
      .catch(async (error: mongodb.MongoServerError) => {
        if (error.codeName === 'NamespaceNotFound') {
          await db.createCollection(schema.collection, {
            validator: schema.json,
          });
        }
      });
  }
}
