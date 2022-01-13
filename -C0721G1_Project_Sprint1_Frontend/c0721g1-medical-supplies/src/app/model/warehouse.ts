import {Supplies} from "./supplies";

export class Warehouse {
  id : number;
  name: string;
  broken_supplies: number;
  normal_supplies: number;
  import_date: string;
  price: number;
  quantity: number;
  unit: string;
  supplies: Supplies;
}
