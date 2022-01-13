import {Component, DoCheck, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {RegisterForm} from '../../model/registerForm';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

/*
Creator: PhuocPD
 */
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    // tslint:disable-next-line:max-line-length
    username: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(20), Validators.pattern('^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){4,18}[a-zA-Z0-9]$')]),
    // tslint:disable-next-line:max-line-length
    password: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(20), Validators.pattern('^[a-zA-Z0-9]{6,20}$')]),
    role: new FormControl('', [Validators.required])
  });
  validateUsernameMessage = '';
  validatePasswordMessage = '';
  validateUsernameExistMessage = '';
  registerModel: RegisterForm;
  codeInput: string;
  formStatus = false;
  buttonUpdateStatus = false;
  buttonRegisterStatus = false;
  buttonCheckStatus = true;
  h2UpdateStatus = false;
  h2RegisterStatus = true;
  disabledStatus = false;

  constructor(private authService: AuthService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  checkCode() {
    this.authService.getRegister(this.codeInput).subscribe(value => {
      if (value === 1) {
        this.toastrService.warning('Mã nhân viên không tồn tại.', 'Tin nhắn từ hệ thống');
      } else if (value === 2) {
        // tslint:disable-next-line:max-line-length
        this.toastrService.info('Mã nhân viên tồn tại nhưng hiện tại chưa có tài khoản. Vui lòng tạo tài khoản cho mã nhân viên này.', 'Tin nhắn từ hệ thống');
        this.formStatus = true;
        this.buttonRegisterStatus = true;
        this.buttonCheckStatus = false;
      } else {
        this.registerModel = value;
        this.registerForm.setValue(this.registerModel);
        this.formStatus = true;
        this.buttonUpdateStatus = true;
        this.buttonCheckStatus = false;
        this.h2RegisterStatus = false;
        this.h2UpdateStatus = true;
        this.disabledStatus = true;
      }
    });
  }

  register(): void {
    this.authService.register(this.registerForm.value, this.codeInput).subscribe(value => {
      this.toastrService.success(value.message, 'Tin nhắn từ hệ thống');
      this.router.navigateByUrl('/system');
    }, error => {
      this.validateUsernameExistMessage = error.error;
      this.validateUsernameMessage = error.error.username;
      this.validatePasswordMessage = error.error.password;
    });
  }

  update(): void {
    this.authService.editRegister(this.registerForm.value, this.codeInput).subscribe(value => {
      this.toastrService.success(value.message, 'Tin nhắn từ hệ thống');
      this.router.navigateByUrl('/system');
    }, error => {
      this.validatePasswordMessage = error.error.password;
    });
  }

  get username() {
    return this.registerForm.get('username');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get role() {
    return this.registerForm.get('role');
  }
}
