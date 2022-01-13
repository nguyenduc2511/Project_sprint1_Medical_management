import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {TokenStorageService} from '../../service/token-storage.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {MatDialogRef} from '@angular/material/dialog';
import {CookieService} from 'ngx-cookie-service';
import {LoginRemember} from '../../model/LoginRemember';

/*
Creator: PhuocPD
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    // tslint:disable-next-line:max-line-length
    username: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(20), Validators.pattern('^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){4,18}[a-zA-Z0-9]$')]),
    // tslint:disable-next-line:max-line-length
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(20), Validators.pattern('^[a-zA-Z0-9]{6,20}$')]),
    remember: new FormControl(false)
  });
  username: string;
  successMessage = '';
  roles: string[] = [];
  returnUrl: string;
  loginRemember: LoginRemember;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private tokenStorageService: TokenStorageService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toastrService: ToastrService,
              private matDialogRef: MatDialogRef<LoginComponent>,
              private cookieService: CookieService) {
    if (cookieService.get('remember') === 'Yes') {
      this.loginForm.value.username = this.cookieService.get('username');
      this.loginForm.value.password = this.cookieService.get('password');
      this.loginRemember = this.loginForm.value;

    }
  }

  ngOnInit(): void {
    this.loginForm.setValue(this.loginRemember);
    this.returnUrl = this.activatedRoute.snapshot.queryParams.returnUrl;
    if (this.tokenStorageService.getToken()) {
      const user = this.tokenStorageService.getUser();
      this.authService.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
      this.username = this.tokenStorageService.getUser().username;
    }
  }

  onSubmit(): void {
    this.authService.login(this.loginForm.value).subscribe(value => {
      if (this.loginForm.value.remember) {
        this.cookieService.set('remember', 'Yes', 365);
        this.cookieService.set('username', this.loginForm.value.username, 365);
        this.cookieService.set('password', this.loginForm.value.password, 365);
        this.tokenStorageService.saveTokenLocal(value.token);
        this.tokenStorageService.saveUserLocal(value);
      } else {
        this.cookieService.set('remember', 'No');
        this.cookieService.set('username', '');
        this.cookieService.set('password', '');
        this.tokenStorageService.saveTokenSession(value.token);
        this.tokenStorageService.saveUserSession(value);
      }
      this.authService.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
      this.username = this.tokenStorageService.getUser().username;
      if (this.returnUrl) {
        this.router.navigateByUrl(this.returnUrl);
      } else {
        this.router.navigateByUrl('/system');
      }
      this.matDialogRef.close();
      this.toastrService.success('Đăng nhập thành công.', 'Tin nhắn từ hệ thống');
    }, error => {
      this.toastrService.error('Đăng nhập thất bại.', 'Tin nhắn từ hệ thống');
      this.authService.isLoggedIn = false;
    });
  }
}
