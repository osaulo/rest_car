import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListUserComponent } from './list-user/list-user.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { DetailUserComponent } from './detail-user/detail-user.component';


const routes: Routes = [
  { path:"users", component:ListUserComponent },
  { path:"create-user", component:CreateUserComponent },
  { path:"detail-user/:id", component:DetailUserComponent },
  //{ path:"**", redirectTo:'users' },
  { path: '', redirectTo: 'user', pathMatch: 'full' },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
