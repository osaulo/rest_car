import  { Car } from './Car';

export class User {
    id:string;
    firstName:string;
    lastName:string;
    email:string;
    birthday:Date;
    login:string;
    password:string;
    phone:string;
    cars:Array<Car>

    constructor(id:string,
        firstName:string,
        lastName:string,
        email:string,
        birthday:Date,
        login:string,
        password:string,
        phone:string){
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.birthday = birthday;
            this.login = login;
            this.password = password;
            this.phone = phone;
        }
}