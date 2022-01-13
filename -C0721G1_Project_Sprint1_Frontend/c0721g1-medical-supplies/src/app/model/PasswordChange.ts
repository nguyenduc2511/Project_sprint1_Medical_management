export class PasswordChange {
  // tslint:disable-next-line:variable-name
  private _oldPassword: string;
  // tslint:disable-next-line:variable-name
  private _newPassword: string;

  constructor() {
  }

  get oldPassword(): string {
    return this._oldPassword;
  }

  set oldPassword(value: string) {
    this._oldPassword = value;
  }

  get newPassword(): string {
    return this._newPassword;
  }

  set newPassword(value: string) {
    this._newPassword = value;
  }
}
