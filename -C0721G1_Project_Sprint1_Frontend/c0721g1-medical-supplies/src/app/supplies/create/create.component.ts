// @ts-ignore
import {Component, Inject, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Producer} from '../../model/producer';
import {SuppliesType} from '../../model/supplies-type';
import {SuppliesService} from '../../service/supplies.service';
import {SuppliesTypeService} from '../../service/supplies-type.service';
import {Router} from '@angular/router';
import {ProducerService} from '../../service/producer.service';
import {Supplies} from '../../model/supplies';
import {ToastrService} from 'ngx-toastr';

import {formatDate} from '@angular/common';
import {finalize} from 'rxjs/operators';
import {AngularFireStorage} from '@angular/fire/storage';
import {addMonths, addYears, differenceInDays, differenceInMonths, differenceInYears} from 'date-fns';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  supplies: Supplies;
  producers: Producer[] = [];
  suppliesTypes: SuppliesType[] = [];
  selectedImage: any = null;
  public errorDatabase = [];
  // tslint:disable-next-line:ban-types
  checkError: Boolean;
  newCode: string;
  // @ts-ignore
  suppliesForm: FormGroup = new FormGroup({
      id: new FormControl(),
      code: new FormControl(),
      name: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.minLength(5)]),
      price: new FormControl('', [Validators.required, Validators.min(0), Validators.pattern('^[0-9]+$')]),
      producer: new FormControl('', [Validators.required]),
      suppliesType: new FormControl('', [Validators.required]),
      // tslint:disable-next-line:max-line-length
      productionDate: new FormControl('', [Validators.required, Validators.pattern('^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$')]),
      // tslint:disable-next-line:max-line-length
      expiryDate: new FormControl('', [Validators.required, this.validateExpiryDate, Validators.pattern('^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$')]),
      introduce: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(500)]),
      technicalInformation: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(500)]),
      image: new FormControl()
    }
  );
  priceBinding: string ;

  constructor(private suppliesService: SuppliesService,
              private suppliesTypeService: SuppliesTypeService,
              private producerService: ProducerService,
              private t: ToastrService,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private router: Router) {
  }


  ngOnInit(): void {
    this.getAllProducer();
    this.getAllSuppliesType();
    this.suppliesService.getCode().subscribe(data => {
      this.supplies = data;
      this.newCode = this.supplies.code;
    });
  }

  getAllSuppliesType() {
    // @ts-ignore
    this.suppliesTypeService.getListSuppliesType().subscribe(data => {
      this.suppliesTypes = data;
    });
  }

  getAllProducer() {
    this.producerService.getListProducer().subscribe(data => {
      this.producers = data;
    });
  }

  showPreview(event: any) {
    this.selectedImage = event.target.files[0];
  }

  submit() {
    // upload image to firebase
    this.supplies = this.suppliesForm.value;
    if (this.selectedImage != null) {
      const nameImg = this.getCurrentDateTime() + this.selectedImage;
      const fileRef = this.storage.ref(nameImg);
      this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
        finalize(() => {
          // tslint:disable-next-line:no-shadowed-variable
          fileRef.getDownloadURL().subscribe((url) => {
            // tslint:disable-next-line:max-line-length
            this.suppliesForm.patchValue({image: url + ''});
            this.suppliesService.save(this.suppliesForm.value).subscribe(() => {
              this.router.navigateByUrl('supplies/list');
              this.t.success('Thêm mới thông tin vật tư thành công', 'Tin nhắn từ hệ thống');
              this.checkError = true;
              // tslint:disable-next-line:no-shadowed-variable
            }, error => {
              this.checkError = false;
              this.handleError(error);
            });
          });
        })
      ).subscribe();
    } else {

      this.suppliesService.save(this.suppliesForm.value).subscribe(() => {
          this.router.navigateByUrl('supplies/list');
          this.t.success('Thêm mới thông tin vật tư thành công', 'Tin nhắn từ hệ thống');
          this.checkError = true;
          // tslint:disable-next-line:no-shadowed-variable
        }, error => {
          this.checkError = false;
          this.handleError(error);
        }
      );
    }
  }

  handleError(code) {
    this.errorDatabase = code.error;
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }

  get code() {
    return this.suppliesForm.get('code');
  }

  get name() {
    return this.suppliesForm.get('name');
  }

  get price() {
    return this.suppliesForm.get('price');
  }

  get suppliesType() {
    return this.suppliesForm.get('suppliesType');
  }

  get producer() {
    return this.suppliesForm.get('producer');
  }

  get productionDate() {
    return this.suppliesForm.get('productionDate');
  }

  get expiryDate() {
    return this.suppliesForm.get('expiryDate');
  }

  get introduce() {
    return this.suppliesForm.get('introduce');
  }

  get technicalInformation() {
    return this.suppliesForm.get('technicalInformation');
  }

  get image() {
    return this.suppliesForm.get('image');
  }

  validateExpiryDate(expiryDate: AbstractControl) {
    return isExpiryDate(expiryDate.value) ? null : {errorExpiryDate: true};
  }
}

function isExpiryDate(date) {
  let diff = getDiffToNow(date);
  diff = Math.abs(diff);
  return (diff / 365) >= 1;
}

function getDiffToNow(diff: string | number | Date): number {
  const result: string[] = [];
  const now = new Date();
  diff = new Date(diff);
  const days1 = differenceInDays(now, diff);
  return days1;
}
