import {Producer} from './producer';
import {SuppliesType} from './supplies-type';

export class Supplies {
  id: number;
  code: string;
  name: string;
  price: number;
  productionDate: string;
  expiryDate: string;
  image: string;
  introduce: string;
  technicalInformation: string;
  producer: Producer;
  suppliesType: SuppliesType;
  status: number;
  // tslint:disable-next-line:variable-name
  producer_id: number;
  // tslint:disable-next-line:variable-name
  supplies_type_id: number;
  // tslint:disable-next-line:variable-name
  expiry_date: string;
  // tslint:disable-next-line:variable-name
  production_date: string;
}
