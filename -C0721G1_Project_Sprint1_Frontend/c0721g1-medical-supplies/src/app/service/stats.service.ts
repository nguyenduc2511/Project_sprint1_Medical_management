import { Injectable } from '@angular/core';
import {FinancialStats} from '../model/FinancialStats';

@Injectable({
  providedIn: 'root'
})
export class StatsService {
  financial: FinancialStats;
  revenue: number;
  totalCost: number;
  profit: number;
  check = 0;
  constructor() { }
  setCheck(value: number) { this.check = value; }
  saveValue(fin: FinancialStats, rev: number, ttCost: number, prof: number) {
    this.financial = fin;
    this.revenue = rev;
    this.totalCost = ttCost;
    this.profit = prof;
  }
  getCheck() {
    return this.check;
  }
  getFinancial() {
    return this.financial;
  }
  getRevenue() {
    return this.revenue;
  }
  getTotalCost() {
    return this.totalCost;
  }
  getProfit() {
    return this.profit;
  }
}

