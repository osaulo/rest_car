import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../entity/User';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl
} from '@angular/forms';

@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html'
})
export class DetailUserComponent implements OnInit {

  form: FormGroup;
  id: number;
  user: User;
  birthday: any;

  constructor(private route: ActivatedRoute,private router: Router,
    private userService: UserService, private formBuilder: FormBuilder) { }

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

    this.id = this.route.snapshot.params['id'];
    
    this.userService.findUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
        let date = new Date(this.user.birthday);
        this.birthday = { year: date.getFullYear(), 
          month: date.getMonth()+1, day: date.getDate() };
      }, error => console.log(error));
  }

  updateUser() {
    this.userService.updateUser(this.user, this.id)
      .subscribe(data => console.log(data), error => console.log(error));
    this.gotoList();
  }

  onSubmit() {
    console.log(this.form);
    if (this.form.valid) {
      console.log('form submitted');
      let date = this.form.controls["birthday"].value;
      this.user = new User(
        this.user.id,
        this.form.controls["firstName"].value,
        this.form.controls["lastName"].value,
        this.form.controls["email"].value,
        new Date(date['year'],date['month']-1,date['day']),
        this.form.controls["login"].value,
        this.form.controls["password"].value,
        this.form.controls["phone"].value
        );
        console.log(this.user);
      this.updateUser();
    } else {
      this.validateAllFormFields(this.form);
    }
    this.updateUser();    
  }

  gotoList() {
    this.router.navigate(['/users']);
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
}
