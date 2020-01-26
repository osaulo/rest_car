import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { ListUserComponent } from './list-user/list-user.component';
import { DetailUserComponent } from './detail-user/detail-user.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { InputMaskModule } from 'racoon-mask-raw';
import { FieldErrorDisplayComponent } from './field-error-display/field-error-display.component';
import { CreateCarComponent } from './create-car/create-car.component';
import { ListCarComponent } from './list-car/list-car.component';
import { DetailCarComponent } from './detail-car/detail-car.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateUserComponent,
    ListUserComponent,
    DetailUserComponent,
    FieldErrorDisplayComponent,
    CreateCarComponent,
    ListCarComponent,
    DetailCarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    InputMaskModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
