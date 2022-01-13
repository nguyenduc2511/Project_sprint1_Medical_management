import {Component, OnInit} from '@angular/core';
import {Employee} from '../../model/employee';
import {EmployeeService} from '../../service/employee.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  employee: Employee;
  id: number;
  role: string;

  constructor(private employeeService: EmployeeService,
              private active: ActivatedRoute,
              private tokenStorageService: TokenStorageService,
              private router: Router) {
    console.log();
    console.log();
    if (this.tokenStorageService.getUser().employee.id !== Number(this.active.snapshot.params.id)) {
      this.router.navigateByUrl('/auth/forbidden');
    }
    this.id = this.active.snapshot.params.id;
    this.getDetail();
    if (this.tokenStorageService.getUser().roles.length === 2) {
      this.role = 'admin';
    } else {
      this.role = 'user';

    }
  }

  ngOnInit(): void {
  }


  getDetail() {
    this.employeeService.findByIdByUser(this.id).subscribe(value => {
      // @ts-ignore
      this.employee = value;
    });
  }
}
