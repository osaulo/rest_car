import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../entity/User';

@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html'
})
export class DetailUserComponent implements OnInit {

  id: number;
  user: User;

  constructor(private route: ActivatedRoute,private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    //this.user = new User();

    this.id = this.route.snapshot.params['id'];
    
    this.userService.findUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  updateUser() {
    this.userService.updateUser(this.user, this.id)
      .subscribe(data => console.log(data), error => console.log(error));
    //this.user = new User();
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();    
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
}
