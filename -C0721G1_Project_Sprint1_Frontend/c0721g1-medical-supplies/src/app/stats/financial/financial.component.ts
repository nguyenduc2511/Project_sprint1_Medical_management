import {Component, HostListener, OnChanges, OnInit, ViewChild} from '@angular/core';
import {FinancialStats} from '../../model/FinancialStats';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {FinancialService} from '../../service/financial.service';
import {StatsService} from '../../service/stats.service';
import {BsDatepickerDirective} from 'ngx-bootstrap/datepicker';

@Component({
  selector: 'app-financial',
  templateUrl: './financial.component.html',
  styleUrls: ['./financial.component.css']
})
export class FinancialComponent implements OnInit {

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private financialService: FinancialService,
              private stastService: StatsService) {
    this.orderMoney = financialService.getTotal();
    console.log(this.orderMoney);
    if (this.orderMoney === undefined) {
      this.orderMoney = 0;
    }
    this.checkSearch = this.stastService.getCheck();
    if (this.checkSearch === 0) {
      this.financialService.getAll().subscribe(
        value => {
          this.financial = value;
          this.income = this.financial.income + this.orderMoney;
          this.revenue = this.financial.income;
          this.totalCost = (this.financial.refund + this.financial.cancelled + this.financial.importMoney);
          this.profit = (this.revenue - this.totalCost);
          this.createRevenueChart(this.labelsRevenue, this.dataRevenue, 'myChart1');
          this.createDetailChart(this.labelsDetail, this.dataDetail, 'myChart');
        }
      );
    }
  }

  @ViewChild(BsDatepickerDirective, {static: false}) datepicker?: BsDatepickerDirective;
  orderMoney = 0;
  financial: FinancialStats;
  bsValue = new Date();
  date: string;
  revenue: number;
  totalCost: number;
  profit: number;
  canvas: any;
  ctx: any;
  // content chart chi tiet
  ctxDetails: any;
  labelsRevenue: [];
  dataRevenue: [];
  // content chart chi tiet
  labelsDetail: [];
  dataDetail: [];
  check = false;
  dateSearch: string;
  checkSearch: number;
  income: number;

  chart = null;

  chartDetail = null;


  @HostListener('window:scroll')
  onScrollEvent() {
    this.datepicker?.hide();
  }

  // chart doanh thu
  createRevenueChart(labels, data, myChart1) {
    if (this.chart != null) {
      this.chart.destroy();
    }
    this.canvas = document.getElementById('myChart1');
    this.ctx = this.canvas.getContext('2d');
    // @ts-ignore
    this.chart = new Chart(this.ctx, {
      type: 'bar',
      data: {
        labels: ['Tổng thu', 'Tổng chi', 'Lợi nhuận'],
        datasets: [{
          label: 'Hiển thị bảng',

          data: [this.revenue, this.totalCost, this.profit],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)'
          ],
          borderWidth: 1
        }]
      },
      // ẩn cái label tiêu đề
      options: {
        plugins: {
          legend: {
            display: false
          }
        }
      }
    });
  }

  createDetailChart(labelsDetail: [], dataDetail: [], myChart: string) {
    if (this.chartDetail != null) {
      this.chartDetail.destroy();
    }
    this.canvas = document.getElementById('myChart');
    this.ctxDetails = this.canvas.getContext('2d');
    // @ts-ignore
    this.chartDetail = new Chart(this.ctxDetails, {
      type: 'pie',
      data: {
        labels: [
          'Bán Hàng',
          'Nhập từ nhà cung cấp',
          'Nhập hàng',
          'Trả hàng',
          'Hủy hàng'
        ],
        datasets: [{
          label: 'My First Dataset',
          // tslint:disable-next-line:max-line-length
          data: [this.income, this.financial.returnMoney, this.financial.importMoney, this.financial.refund, this.financial.cancelled],
          backgroundColor: [
            'rgb(255, 99, 132)',
            'rgb(75, 192, 192)',
            'rgb(255, 205, 86)',
            'rgb(201, 203, 207)',
            'rgb(54, 162, 235)'
          ]
        }]
      },
    });
  }

// search cua binh
  search() {
    if (this.bsValue.getMonth() < 9 && this.bsValue.getDate() < 10) {
      this.date = this.bsValue.getFullYear().toString()
        + '-0' + (this.bsValue.getMonth() + 1).toString()
        + '-0' + this.bsValue.getDate().toString();
    } else if (this.bsValue.getMonth() < 9) {
      this.date = this.bsValue.getFullYear().toString()
        + '-0' + (this.bsValue.getMonth() + 1).toString()
        + '-' + this.bsValue.getDate().toString();
    } else if (this.bsValue.getDate() < 10) {
      this.date = this.bsValue.getFullYear().toString()
        + '-' + (this.bsValue.getMonth() + 1).toString()
        + '-0' + this.bsValue.getDate().toString();
    } else {
      this.date = this.bsValue.getFullYear().toString()
        + '-' + (this.bsValue.getMonth() + 1).toString()
        + '-' + this.bsValue.getDate().toString();
    }
    console.log(this.date);
    this.financialService.searchFinancialStats(this.date).subscribe(
      value => {
        this.financial = value;

        if (this.financial.income == null &&
          this.financial.cancelled == null &&
          this.financial.importMoney == null &&
          this.financial.refund == null &&
          this.financial.returnMoney == null) {
          this.chartDetail.destroy();
          this.chart.destroy();
          this.revenue = null;
          this.totalCost = null;
          this.profit = null;
          this.check = true;

        } else {
          this.check = false;
          this.chartDetail.destroy();
          this.chart.destroy();
          this.revenue = this.financial.income;
          this.totalCost = (this.financial.refund + this.financial.cancelled + this.financial.importMoney);
          this.profit = (this.revenue - this.totalCost);
          this.createDetailChart(this.labelsDetail, this.dataDetail, 'myChart');
          this.createRevenueChart(this.labelsRevenue, this.dataRevenue, 'myChart1');
        }
      }
    );
  }

  onPrint() {
    window.print();
  }

  ngOnInit(): void {
  }
}

// search cua binh
// search() {
//   // this.router.navigateByUrl('supplies-stats').then( s => {
//   //   this.router.navigateByUrl('financial/'+this.date);
//   // });
//   this.date = this.bsValue.getFullYear().toString()
//     + '-' + (this.bsValue.getMonth() + 1).toString()
//     + '-' + this.bsValue.getDate().toString();
//   this.financialsv.searchFinancialStats(this.date).subscribe(
//     value => {
//       this.financial = value;
//
//       if (this.financial.income == null &&
//         this.financial.cancelled == null &&
//         this.financial.importMoney == null &&
//         this.financial.refund == null &&
//         this.financial.returnMoney == null) {
//         this.check = true;
//
//       }
//       this.revenue = this.financial.income;
//       this.totalCost = (this.financial.refund + this.financial.cancelled + this.financial.importMoney);
//       this.profit = (this.revenue - this.totalCost);
//       this.createRevenueChart(this.labelsRevenue, this.dataRevenue, 'myChart1');
//       this.createDetailChart(this.labelsDetail, this.dataDetail, 'myChart');
//
//     }
//   )
// }
