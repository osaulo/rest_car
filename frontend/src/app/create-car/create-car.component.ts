import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { CarService } from '../services/car.service';
import { Router } from '@angular/router';
import { Car } from '../entity/Car';

@Component({
  selector: 'app-create-car',
  templateUrl: './create-car.component.html'
})
export class CreateCarComponent implements OnInit {

  form: FormGroup;

  constructor(private carService: CarService,
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

  save(car: Car) {
    this.carService.createCar(car)
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
      let car = new Car(
        undefined,
        this.form.controls["year"].value,
        this.form.controls["licensePlate"].value,
        this.form.controls["model"].value,
        this.form.controls["color"].value
        );
        console.log(car);
      this.save(car);
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
    this.router.navigate(['/cars']);
  }
}
