import { Component, OnInit } from '@angular/core';
import { Car } from '../entity/Car';
import { CarService } from '../services/car.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html'
})
export class ListCarComponent implements OnInit {

  carList: Array<Car>;

  // Service injected in constructor
  constructor(private carService:CarService, private router: Router) { }

  // Initializes all cars list from car service
  ngOnInit() {
    this.refreshList();
  }

  // Method to create an car
  createCar(){
    this.router.navigate(["create-car"]);
  }

  // Method to refresh the car list after successful delete
  refreshList() {
    this.carService.list()
    .subscribe(
      data => {
        console.log(data);
        this.carList = data;
      },
      error => console.log(error));
  }

  deleteCar(id: number) {
    this.carService.deleteCar(id)
      .subscribe(
        data => {
          console.log(data);
          this.refreshList();
        },
        error => console.log(error));
  }

  carDetails(id: number){
    this.router.navigate(['detail-car', id]);
  }
}
