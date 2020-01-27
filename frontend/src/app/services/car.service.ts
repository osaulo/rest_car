import { Injectable } from '@angular/core';
import { Car } from '../entity/Car';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private baseUrl = 'http://localhost:8080/microservice/cars';

  constructor(private http:HttpClient) { }

  // Returns all the cars
  list() : Observable<Array<Car>>{
    return this.http.get(`${this.baseUrl}`).pipe(
      map(data => <Array<Car>> data['data'])
    );
  }

  // Create an car
  createCar(car:Car) : Observable<Car> {
    return this.http.post(`${this.baseUrl}`, car).pipe(
      map(data => <Car> data)
    );
  }

  // Update car details
  updateCar(car:Car, id:number) : Observable<Car> {
    return this.http.put(`${this.baseUrl}/${id}`, car).pipe(
      map(data => <Car> data)
    );
  }

  // Deletes an car
  deleteCar(id:number) : Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  // Returns an car with passed car id
  findCar(id:number) : Observable<Car>{
    return this.http.get(`${this.baseUrl}/${id}`).pipe(
      map(data => <Car> data)
    );
  }
}
