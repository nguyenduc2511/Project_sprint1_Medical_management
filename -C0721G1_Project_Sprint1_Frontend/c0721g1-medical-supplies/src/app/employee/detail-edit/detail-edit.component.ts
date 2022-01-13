import {Component, Inject, OnInit} from '@angular/core';
import {Position} from '../../model/position';
import {Employee} from '../../model/employee';
import {User} from '../../model/user';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {EmployeeService} from '../../service/employee.service';
import {PositionService} from '../../service/position.service';
import {UserService} from '../../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AngularFireStorage} from '@angular/fire/storage';
import {finalize} from 'rxjs/operators';
import {formatDate} from '@angular/common';
import {ToastrService} from 'ngx-toastr';
import {TokenStorageService} from '../../service/token-storage.service';

@Component({
  selector: 'app-detail-edit',
  templateUrl: './detail-edit.component.html',
  styleUrls: ['./detail-edit.component.css']
})
export class DetailEditComponent implements OnInit {

  employee: Employee;
  positions: Position[] = [];
  users: User[] = [];
  public errorDB = [];
  selectedImage: any = null;
  id: number;
  urlImage = 'https://i.imgur.com/7Vtlcpx.png';
  // tslint:disable-next-line:ban-types
  checkerr: Boolean;

  employeeEdit: Employee;

  employeeDetailEditForm = new FormGroup({
    id: new FormControl(''),
    code: new FormControl(''),
    name: new FormControl('', [Validators.required]),
    // tslint:disable-next-line:max-line-length
    birthday: new FormControl('', [Validators.required, Validators.pattern('^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$')]),
    image: new FormControl(),
    gender: new FormControl('', [Validators.required]),
    // tslint:disable-next-line:max-line-length
    phone: new FormControl('', [Validators.required, Validators.pattern('^(0|(\\(84\\)\\+))+([9][0-1][0-9]{7})$')]),
    // tslint:disable-next-line:max-line-length
    address: new FormControl('', [Validators.required]),
    position: new FormControl('', [Validators.required]),
    user: new FormControl()
  });
  role: string;

  constructor(private http: HttpClient,
              private employeeService: EmployeeService,
              private positionService: PositionService,
              private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private toastrService: ToastrService,
              private tokenStorageService: TokenStorageService) {
    if (this.tokenStorageService.getUser().employee.id !== Number(this.activatedRoute.snapshot.params.id)) {
      this.router.navigateByUrl('/auth/forbidden');
    }
    this.id = this.activatedRoute.snapshot.params.id;
    this.findById();
    if (this.tokenStorageService.getUser().roles.length === 2) {
      this.role = 'admin';
    } else {
      this.role = 'user';
    }
  }


  pos(p1: Position, p2: Position): boolean {
    return p1 && p2 ? p1.id === p2.id : p1 === p2;
  }


  ngOnInit(): void {
    this.getAllPosition();
    this.getAllUser();
  }


  findById() {
    // @ts-ignore
    this.employeeService.findByIdEmployeeDetail(this.id).subscribe(data => {
      // @ts-ignore
      this.employee = data;
      console.log(this.employee);
      if (this.employee.image !== null) {
        this.urlImage = this.employee.image;
      }
      this.employeeDetailEditForm.setValue(this.employee);
    });
  }


  getAllPosition() {
    this.positionService.getListPosition().subscribe(data => {
      // @ts-ignore
      this.positions = data;
    });
  }

  showPreview(event: any) {
    this.selectedImage = event.target.files[0];
  }


  submit() {
    // upload image to firebase
    if (this.selectedImage != null) {
      const nameImg = this.getCurrentDateTime() + this.selectedImage;
      const fileRef = this.storage.ref(nameImg);
      this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
        finalize(() => {
          // tslint:disable-next-line:no-shadowed-variable
          fileRef.getDownloadURL().subscribe((url) => {
            // tslint:disable-next-line:max-line-length
            this.employeeDetailEditForm.patchValue({image: url + ''});
            this.employeeService.updateEmployeeDetail(this.id, this.employeeDetailEditForm.value).subscribe(() => {
              this.router.navigate(['/employee/detail/', this.id]);
              this.toastrService.success('Cập nhật thông tin nhân viên thành công.', 'Tin nhắn từ hệ thống');
              this.checkerr = true;
            }, error => {
              this.checkerr = false;
              this.handleError(error);
            });
          });
        })
      ).subscribe();
    } else {
      this.employeeService.updateEmployeeDetail(this.id, this.employeeDetailEditForm.value).subscribe(() => {
        this.router.navigate(['/employee/detail/', this.id]);
        this.toastrService.success('Cập nhật thông tin nhân viên thành công.', 'Tin nhắn từ hệ thống');
        this.checkerr = true;
      }, error => {
        this.handleError(error);
        this.checkerr = false;
      });
    }
  }

  handleError(code) {
    this.errorDB = code.error;
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }

  getAllUser() {
    this.userService.getAllUser().subscribe(data => {
      this.users = data;
    });
  }


  comparePosition(c1: Position, c2: Position): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  compareUser(c1: User, c2: User): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  // get id() {
  //   return this.employeeDetailEditForm.get('id');
  // }

  get code() {
    return this.employeeDetailEditForm.get('code');
  }

  get name() {
    return this.employeeDetailEditForm.get('name');
  }

  get birthday() {
    return this.employeeDetailEditForm.get('birthday');
  }

  get image() {
    return this.employeeDetailEditForm.get('image');
  }

  get gender() {
    return this.employeeDetailEditForm.get('gender');
  }

  get phone() {
    return this.employeeDetailEditForm.get('phone');
  }

  get address() {
    return this.employeeDetailEditForm.get('address');
  }

  get position() {
    return this.employeeDetailEditForm.get('position');
  }

  get user() {
    return this.employeeDetailEditForm.get('user');
  }

}
