import * as mongodb from 'mongodb';

export interface PlayerModel {
  _id?: mongodb.ObjectId;
  session_id: mongodb.ObjectId;
  display_name: string;
  color: string;
  position: mongodb.Int32;
}

export interface GameModel {
  _id?: mongodb.ObjectId;
  name: string;
  turn_length: mongodb.Int32;
  turn_length_units: TimeUnits;
  join_code: string;
}

export enum TimeUnits {
  Seconds = 's',
  Minutes = 'min',
}
