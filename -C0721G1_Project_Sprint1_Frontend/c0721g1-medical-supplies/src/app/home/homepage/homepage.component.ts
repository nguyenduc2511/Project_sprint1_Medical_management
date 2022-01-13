import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RequestMail} from '../../model/RequestMail';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RequestMailService} from '../../service/request-mail.service';
import * as AOS from 'aos';
import {SuppliesService} from '../../service/supplies.service';
import {Supplies} from '../../model/supplies';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  myButton: any;
  suppliesList: Supplies[];
  requestMail: RequestMail;
  requestForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required,
      Validators.pattern('^[a-z]+[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+\\.*[a-zA-Z0-9])*')]),
    content: new FormControl('', Validators.required)
  });
  scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
      document.getElementById('myBtn').style.display = 'block';
    } else {
      document.getElementById('myBtn').style.display = 'none';
    }
  }

  constructor(private requestMailService: RequestMailService,
              private suppliesService: SuppliesService ) {
    this.suppliesService.findAllSupplies().subscribe(value => {
      this.suppliesList = value;
    });
  }

  ngOnInit(): void {
    AOS.init(
      {
        offset: 12,
        duration: 1200,
      }
    );
    this.myButton = document.getElementById('myBtn');
    console.log(this.myButton);
    window.addEventListener('scroll', this.scrollFunction, true);
  }

  sendMail() {
    this.requestMail = this.requestForm.value;
    this.requestMailService.sendEmail(this.requestMail).subscribe();
    this.requestForm.reset();
  }

  topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0;
  }
}
