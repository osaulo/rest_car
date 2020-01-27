import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html'
})
export class ListCarComponent implements OnInit {

  userList: Array<User>;

  // Service injected in constructor
  constructor(private userService:UserService, private router: Router) { }

  // Initializes all users list from user service
  ngOnInit() {
    this.refreshList();
  }

  // Method to create an user
  createUser(){
    this.router.navigate(["create-user"]);
  }

  // Method to refresh the user list after successful delete
  refreshList() {
    this.userService.list()
    .subscribe(
      data => {
        console.log(data);
        this.userList = data;
      },
      error => console.log(error));
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.refreshList();
        },
        error => console.log(error));
  }

  userDetails(id: number){
    this.router.navigate(['detail-user', id]);
  }
}
