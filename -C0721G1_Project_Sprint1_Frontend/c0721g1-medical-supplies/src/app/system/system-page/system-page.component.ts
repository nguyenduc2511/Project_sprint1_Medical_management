import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../../service/token-storage.service';

@Component({
  selector: 'app-system-page',
  templateUrl: './system-page.component.html',
  styleUrls: ['./system-page.component.css']
})
export class SystemPageComponent implements OnInit {

  nameEmployee: string;
  constructor(private tokenStorageService: TokenStorageService) {
    if (this.tokenStorageService.getUser()) {
      this.nameEmployee = this.tokenStorageService.getUser().employee.name;
    }
  }

  ngOnInit(): void {
  }

}
