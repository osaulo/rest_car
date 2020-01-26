import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListUserComponent } from './list-user/list-user.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { DetailUserComponent } from './detail-user/detail-user.component';
import { ListCarComponent } from './list-car/list-car.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { DetailCarComponent } from './detail-car/detail-car.component';


const routes: Routes = [
  { path:"users", component:ListUserComponent },
  { path:"create-user", component:CreateUserComponent },
  { path:"detail-user/:id", component:DetailUserComponent },
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path:"cars", component:ListCarComponent },
  { path:"create-car", component:CreateCarComponent },
  { path:"detail-car/:id", component:DetailCarComponent },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
