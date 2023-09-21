import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatDialogModule } from "@angular/material/dialog";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { ToastrModule } from "ngx-toastr";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AlunoCrudComponent } from "./components/aluno/aluno-crud/aluno-crud.component";
import { AlunoComponent } from "./components/aluno/aluno.component";
import { HeaderComponent } from "./components/header/header.component";
import { HomeComponent } from "./components/home/home.component";
import { InstrutorCrudComponent } from "./components/instrutor/instrutor-crud/instrutor-crud.component";
import { InstrutorComponent } from "./components/instrutor/instrutor.component";
import { LoginComponent } from "./components/login/login.component";
import { NavComponent } from "./components/nav/nav.component";
import { CommonModule } from "@angular/common";
// Firebase services + environment module
import { AngularFireModule } from "@angular/fire/compat";
import { AngularFireAuthModule } from "@angular/fire/compat/auth";
import { AngularFireStorageModule } from "@angular/fire/compat/storage";
import { AngularFirestoreModule } from "@angular/fire/compat/firestore";
import { AngularFireDatabaseModule } from "@angular/fire/compat/database";
import { environment } from "../enviorment/enviorment";
import { AuthService } from "./services/auth.service";
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from "ngx-mask";
import { MatDatepickerModule } from "@angular/material/datepicker";
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    HomeComponent,
    HeaderComponent,
    InstrutorComponent,
    AlunoComponent,
    LoginComponent,
    InstrutorCrudComponent,
    AlunoCrudComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    MatDialogModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    // Forms
    FormsModule,
    ReactiveFormsModule,
    // Requisições http
    HttpClientModule,
    // Angular Material
    MatDatepickerModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatSelectModule,
    MatInputModule,
    MatRadioModule,
    MatTableModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireAuthModule,
    AngularFirestoreModule,
    AngularFireStorageModule,
    AngularFireDatabaseModule,

    NgxMaskDirective,
    NgxMaskPipe,
    ToastrModule.forRoot({
      timeOut: 4000,
      closeButton: true,
      progressBar: true,
    }),
  ],
  providers: [AuthService, provideNgxMask()],
  bootstrap: [AppComponent],
})
export class AppModule {}
