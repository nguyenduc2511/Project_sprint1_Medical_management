import {Component, OnInit} from '@angular/core';
import {PotentialCustomer} from '../../model/PotentialCustomer';
import {Router} from '@angular/router';
import {PotentialCustomerService} from '../../service/potential-customer.service';

@Component({
  selector: 'app-potential-customer',
  templateUrl: './potential-customer.component.html',
  styleUrls: ['./potential-customer.component.css']
})
export class PotentialCustomerComponent implements OnInit {

  constructor(private router: Router,
              private potentialCustomerService: PotentialCustomerService) {
    this.potentialCustomerService.getAll().subscribe(value => {
      this.potentialArr = value;
      this.maxDate.setDate(this.maxDate.getDate() + 7);
      this.bsRangeValue = [this.bsValue, this.maxDate];
      this.getName(value);
      this.potentialCustomerChart(this.labels, this.data, 'myChart');
    });
  }

  potentialArr: PotentialCustomer[];
  startDate: string;
  endDate: string;
  canvas: any;
  ctx: any;
  bsRangeValue: Date[];
  maxDate = new Date();
  bsValue = new Date();
  check = false;
  labels: [];
  page = 1;

  data = {
    labels: []
    ,
    datasets: [
      {
        type: 'bar',
        label: 'Doanh Số',
        data: [],
        borderColor: 'rgb(255, 99, 132)',
        backgroundColor: 'rgba(255, 99, 132, 0.2)'
      },

      {
        type: 'line',
        label: 'Giá trị',
        data: [],
        fill: false,
        borderColor: 'rgb(54, 162, 235)'
      }]
  };

  chart = null;

  ngOnInit(): void {


  }

  private getName(arr: PotentialCustomer[]) {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < arr.length; i++) {
      this.data.labels.push(arr[i].name);
      this.data.datasets[0].data.push(arr[i].quantity);
      this.data.datasets[1].data.push(arr[i].total);
    }
  }
  private potentialCustomerChart(labels, data, myChart) {
    if (this.chart != null) {
      this.chart.destroy();
    }
    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    // @ts-ignore
    this.chart = new Chart(this.ctx, {
      type: 'scatter',
      data: this.data,
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
  onPrint() {
    window.print();
  }
  search() {
    if (this.bsRangeValue[0].getMonth() < 9 && this.bsRangeValue[0].getDate() < 10) {
      this.startDate = this.bsRangeValue[0].getFullYear().toString()
        + '-0' + (this.bsRangeValue[0].getMonth() + 1).toString()
        + '-0' + this.bsRangeValue[0].getDate().toString();
    } else if (this.bsRangeValue[0].getMonth() < 9) {
      this.startDate = this.bsRangeValue[0].getFullYear().toString()
        + '-0' + (this.bsRangeValue[0].getMonth() + 1).toString()
        + '-' + this.bsRangeValue[0].getDate().toString();
    } else if (this.bsRangeValue[0].getDate() < 10) {
      this.startDate = this.bsRangeValue[0].getFullYear().toString()
        + '-' + (this.bsRangeValue[0].getMonth() + 1).toString()
        + '-0' + this.bsRangeValue[0].getDate().toString();
    } else {
      this.startDate = this.bsRangeValue[0].getFullYear().toString()
        + '-' + (this.bsRangeValue[0].getMonth() + 1).toString()
        + '-' + this.bsRangeValue[0].getDate().toString();
    }
    if (this.bsRangeValue[1].getMonth() < 9 && this.bsRangeValue[1].getDate() < 10) {
      this.endDate = this.bsRangeValue[1].getFullYear().toString()
        + '-0' + (this.bsRangeValue[1].getMonth() + 1).toString()
        + '-0' + this.bsRangeValue[1].getDate().toString();
    } else if (this.bsRangeValue[1].getMonth() < 9) {
      this.endDate = this.bsRangeValue[1].getFullYear().toString()
        + '-0' + (this.bsRangeValue[1].getMonth() + 1).toString()
        + '-' + this.bsRangeValue[1].getDate().toString();
    } else if (this.bsRangeValue[1].getDate() < 10) {
      this.endDate = this.bsRangeValue[1].getFullYear().toString()
        + '-' + (this.bsRangeValue[1].getMonth() + 1).toString()
        + '-0' + this.bsRangeValue[1].getDate().toString();
    } else {
      this.endDate = this.bsRangeValue[1].getFullYear().toString()
        + '-' + (this.bsRangeValue[1].getMonth() + 1).toString()
        + '-' + this.bsRangeValue[1].getDate().toString();
    }
    this.potentialCustomerService.searchCustomerStats(this.startDate, this.endDate).subscribe(value => {
      this.chart.destroy();
      this.check = false;
      this.potentialArr = value;
      this.potentialCustomerChart(this.labels, this.data, 'myChart');

    }, error => {
      this.chart.destroy();
      this.check = true;
      this.potentialArr = [];
    });

  }
}
