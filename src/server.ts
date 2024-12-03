import * as dotenv from 'dotenv';
import express from 'express';
import cors from 'cors';
import { connectToDatabase } from './database';
import { gameRouter } from './game.routes';

dotenv.config({ path: '../.env' });

const { ATLAS_URI, EXPRESS_PORT } = process.env;

if (!ATLAS_URI) {
  console.error(
    'No ATLAS_URI environment variable has been defined in config.env'
  );
  process.exit(1);
}

connectToDatabase(ATLAS_URI)
  .then(() => {
    const app = express();
    app.use(cors());

    app.use('/game', gameRouter);
    // start the Express server
    app.listen(EXPRESS_PORT, () => {
      console.log(`Server running at http://localhost:${EXPRESS_PORT} ...`);
    });
  })
  .catch((error) => console.error(error));
