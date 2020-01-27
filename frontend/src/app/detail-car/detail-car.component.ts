import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Car } from '../entity/Car';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../services/car.service';

@Component({
  selector: 'app-detail-car',
  templateUrl: './detail-car.component.html'
})
export class DetailCarComponent implements OnInit {

  form: FormGroup;
  id: number;
  car: Car;
  birthday: any;

  constructor(private route: ActivatedRoute,private router: Router,
    private carService: CarService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      year: [null, Validators.required],
      licensePlate: [null, Validators.required],
      model: [null, Validators.required],
      color: [null, Validators.required],
    });

    this.id = this.route.snapshot.params['id'];
    
    this.carService.findCar(this.id)
      .subscribe(data => {
        console.log(data)
        this.car = data;
      }, error => console.log(error));
  }

  updateCar() {
    this.carService.updateCar(this.car, this.id)
      .subscribe(data => console.log(data), error => console.log(error));
    this.gotoList();
  }

  onSubmit() {
    console.log(this.form);
    if (this.form.valid) {
      console.log('form submitted');
      this.car = new Car(
        this.car.id,
        this.form.controls["year"].value,
        this.form.controls["licensePlate"].value,
        this.form.controls["model"].value,
        this.form.controls["color"].value
        );
        console.log(this.car);
      this.updateCar();
    } else {
      this.validateAllFormFields(this.form);
    }
    this.updateCar();    
  }

  gotoList() {
    this.router.navigate(['/cars']);
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
