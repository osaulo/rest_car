import { Injectable } from '@angular/core';
import { User } from '../entity/User';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/microservice/users';

  constructor(private http:HttpClient) { }

  // Returns all the users
  list() : Observable<Array<User>>{
    return this.http.get(`${this.baseUrl}`).pipe(
      map(data => <Array<User>> data['data'])
    );
  }

  // Create an user
  createUser(user:User) : Observable<User> {
    return this.http.post(`${this.baseUrl}`, user).pipe(
      map(data => <User> data)
    );
  }

  // Update user details
  updateUser(user:User, id:number) : Observable<User> {
    return this.http.put(`${this.baseUrl}/${id}`, user).pipe(
      map(data => <User> data)
    );
  }

  // Deletes an user
  deleteUser(id:number) : Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  // Returns an user with passed user id
  findUser(id:number) : Observable<User>{
    return this.http.get(`${this.baseUrl}/${id}`).pipe(
      map(data => <User> data)
    );
  }
}
