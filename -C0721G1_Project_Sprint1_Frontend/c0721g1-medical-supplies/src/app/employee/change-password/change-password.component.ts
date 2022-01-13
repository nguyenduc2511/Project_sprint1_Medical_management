import {Component, DoCheck, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../service/user.service';
import {ToastrService} from 'ngx-toastr';
import {PasswordChange} from '../../model/PasswordChange';
import {TokenStorageService} from '../../service/token-storage.service';

function passwordMatch(control: AbstractControl) {
  const v = control.value;
  return (v.newPassword === v.confirmNewPassword) ? null : {passwordNotMatch: true};
}

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  passwordChangeForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9]{3,10}$')]),
    newPasswordGroup: new FormGroup({
      newPassword: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9]{3,10}$')]),
      confirmNewPassword: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9]{3,10}$')])
    }, {validators: passwordMatch})
  });
  id: number;
  oldPasswordChange: string;
  newPasswordChange: string;
  errorMessage: string;
  errorMessageForOldPassword: string;

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private toastrService: ToastrService,
              private tokenStorageService: TokenStorageService) {
    if (this.tokenStorageService.getUser().employee.id !== Number(this.activatedRoute.snapshot.params.id)) {
      this.router.navigateByUrl('/auth/forbidden');
    }
    this.id = this.activatedRoute.snapshot.params.id;
  }

  ngOnInit(): void {
  }

  get oldPassword() {
    return this.passwordChangeForm.get('oldPassword');
  }

  get newPassword() {
    return this.passwordChangeForm.get(['newPasswordGroup', 'newPassword']);
  }

  get confirmNewPassword() {
    return this.passwordChangeForm.get(['newPasswordGroup', 'confirmNewPassword']);
  }

  get newPasswordGroup() {
    return this.passwordChangeForm.get('newPasswordGroup');
  }


  onSubmit(passwordChangeForm: FormGroup) {
    console.log(this.passwordChangeForm.value);
    this.oldPasswordChange = this.passwordChangeForm.value.oldPassword;
    this.newPasswordChange = this.passwordChangeForm.value.newPasswordGroup.newPassword;
    this.userService.changePassword(this.id, this.oldPasswordChange, this.newPasswordChange).subscribe(value => {
      if (value === 1) {
        this.errorMessage = 'Mật khẩu mới không được giống mật khẩu cũ.';
      } else if (value === 2) {
        this.errorMessageForOldPassword = 'Mật khẩu cũ không tồn tại.';
      } else {
        this.router.navigateByUrl('/system');
        this.toastrService.success('Cập nhật mật khẩu thành công.', 'Tin nhắn từ hệ thống');
      }
    }, error => {
    });
  }
}
