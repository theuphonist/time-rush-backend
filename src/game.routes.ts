import * as express from 'express';
import { ObjectId } from 'mongodb';
import { collections } from './database';

export const gameRouter = express.Router();
gameRouter.use(express.json());

gameRouter.get('/', async (_req, res) => {
  try {
    const games = await collections?.game?.find({}).toArray();
    res.status(200).send(games);
  } catch (error) {
    res
      .status(500)
      .send(error instanceof Error ? error.message : 'Unknown error');
  }
});

gameRouter.get('/:id', async (req, res) => {
  try {
    const id = req?.params?.id;
    const query = { _id: new ObjectId(id) };
    const game = await collections?.game?.findOne(query);

    if (game) {
      res.status(200).send(game);
    } else {
      res.status(404).send(`Failed to find a game with ID ${id}`);
    }
  } catch (error) {
    res.status(404).send(`Failed to find a game with ID ${req?.params?.id}`);
  }
});

// gameRouter.post('/', async (req, res) => {
//   try {
//     const game = req.body;
//     const result = await collections?.game?.insertOne(game);

//     if (result?.acknowledged) {
//       res.status(201).send(`Created new game: ID ${result.insertedId}.`);
//     } else {
//       res.status(500).send('Failed to create a new game');
//     }
//   } catch (error) {
//     console.error(error);
//     res
//       .status(500)
//       .send(error instanceof Error ? error.message : 'Unknown error');
//   }
// });

// gameRouter.put('/:id', async (req, res) => {
//   try {
//     const id = req?.params?.id;
//     const game = req.body;
//     const query = { _id: new ObjectId(id) };
//     const result = await collections?.game?.updateOne(query, {
//       $set: game,
//     });

//     if (result?.matchedCount) {
//       res.status(200).send(`Updated an game: ID ${id}.`);
//     } else if (!result?.matchedCount) {
//       res.status(404).send(`Failed to find an game: ID ${id}`);
//     } else {
//       res.status(304).send(`Failed to update an game: ID ${id}`);
//     }
//   } catch (error) {
//     const message = error instanceof Error ? error.message : 'Unknown error';
//     console.error(message);
//     res.status(400).send(message);
//   }
// });

// gameRouter.delete('/:id', async (req, res) => {
//   try {
//     const id = req?.params?.id;
//     const query = { _id: new ObjectId(id) };
//     const result = await collections?.game?.deleteOne(query);

//     if (result?.deletedCount) {
//       res.status(202).send(`Removed an game: ID ${id}`);
//     } else if (!result) {
//       res.status(400).send(`Failed to remove an game: ID ${id}`);
//     } else if (!result.deletedCount) {
//       res.status(404).send(`Failed to find an game: ID ${id}`);
//     }
//   } catch (error) {
//     const message = error instanceof Error ? error.message : 'Unknown error';
//     console.error(message);
//     res.status(400).send(message);
//   }
// });
