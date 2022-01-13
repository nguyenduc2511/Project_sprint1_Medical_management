
import {User} from './user';
import {Position} from './position';

export class Employee {
  id: number;
  code: string;
  name: string;
  birthday: string;
  image: string;
  gender: number;
  address: string;
  phone: string;
  user: User;
  position: Position;
}
