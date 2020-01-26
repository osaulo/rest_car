import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl
} from '@angular/forms';
import { User } from '../entity/User';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html'
})
export class CreateUserComponent implements OnInit {
  
  form: FormGroup;

  constructor(private userService: UserService,
    private router: Router,
    private formBuilder: FormBuilder) { }

    ngOnInit() {
      this.form = this.formBuilder.group({
        firstName: [null, Validators.required],
        lastName: [null, Validators.required],
        email: [null, [Validators.required, Validators.email]],
        birthday: [null, Validators.required],
        login: [null, Validators.required],
        password: [null, Validators.required],
        phone: [null, Validators.required],
      });
    }

  save(user: User) {
    this.userService.createUser(user)
      .subscribe(data => {
        console.log(data);
        this.gotoList();
      }, error => console.log(error));
  }

  isFieldValid(field: string) {
    return !this.form.get(field).valid && this.form.get(field).touched;
  }

  displayFieldCss(field: string) {
    return {
      'has-error': this.isFieldValid(field),
      'has-feedback': this.isFieldValid(field)
    };
  }

  onSubmit() {
    console.log(this.form);
    if (this.form.valid) {
      console.log('form submitted');
      let date = this.form.controls["birthday"].value;
      let user = new User(
        undefined,
        this.form.controls["firstName"].value,
        this.form.controls["lastName"].value,
        this.form.controls["email"].value,
        new Date(date['year'],date['month']-1,date['day']),
        this.form.controls["login"].value,
        this.form.controls["password"].value,
        this.form.controls["phone"].value
        );
        console.log(user);
      this.save(user);
    } else {
      this.validateAllFormFields(this.form);
    }
  }

  validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      console.log(field);
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control);
      }
    });
  }

  reset(){
    this.form.reset();
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
