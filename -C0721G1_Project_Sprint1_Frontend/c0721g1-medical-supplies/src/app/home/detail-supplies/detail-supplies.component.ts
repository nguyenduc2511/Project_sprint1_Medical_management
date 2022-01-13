import {Component, OnInit} from '@angular/core';
import {SuppliesService} from '../../service/supplies.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {Supplies} from '../../model/supplies';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-detail-supplies',
  templateUrl: './detail-supplies.component.html',
  styleUrls: ['./detail-supplies.component.css']
})
export class DetailSuppliesComponent implements OnInit {
  constructor(
    private suppliesService: SuppliesService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastrService: ToastrService,
  ) {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.idSupplies = +paramMap.get('id');
    });
  }

  idSupplies: number;
  supplies: Supplies;

  ngOnInit(): void {
    this.suppliesService.findById(this.idSupplies).subscribe(value => {
      this.supplies = value;
    });
    window.scrollTo(0, 0);
  }

  addToCart() {
    this.toastrService.success('Đã thêm thành công ' + this.supplies.name + ' vào giỏ hàng .', 'Tin nhắn từ hệ thống');
    localStorage.setItem(String(this.idSupplies), String(1));
  }

  moveToDetail(id: number) {
    this.router.navigateByUrl('home/detail/' + id );
  }
}
