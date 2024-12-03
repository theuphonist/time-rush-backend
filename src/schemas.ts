import { TimeUnits } from './types';

interface Schema {
  collection: string;
  json: {
    $jsonSchema: Record<string, unknown>;
  };
}

export const schemas: Schema[] = [
  {
    collection: 'game',
    json: {
      $jsonSchema: {
        bsonType: 'object',
        required: ['name', 'turn_length', 'turn_length_units', 'join_code'],
        additionalProperties: false,
        properties: {
          _id: {},
          name: {
            bsonType: 'string',
            description: "'name' is required and is a string",
          },
          turn_length: {
            bsonType: 'int',
            description: "'turn_length' is required and is an int",
          },
          turn_length_units: {
            bsonType: 'string',
            description: `'turn_length_units' is required and one of ${Object.values(
              TimeUnits
            )}`,
            enum: Object.values(TimeUnits),
          },
          join_code: {
            bsonType: 'string',
            description: "'join_code' is required and is a string",
          },
        },
      },
    },
  },
];
