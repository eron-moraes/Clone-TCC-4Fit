import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "./auth/auth.guard";
import { AlunoCrudComponent } from "./components/aluno/aluno-crud/aluno-crud.component";
import { AlunoComponent } from "./components/aluno/aluno.component";
import { HomeComponent } from "./components/home/home.component";
import { InstrutorCrudComponent } from "./components/instrutor/instrutor-crud/instrutor-crud.component";
import { InstrutorComponent } from "./components/instrutor/instrutor.component";
import { LoginComponent } from "./components/login/login.component";
import { NavComponent } from "./components/nav/nav.component";

const routes: Routes = [
  { path: "login", component: LoginComponent },
  {
    path: "",
    canActivate: [AuthGuard],
    component: NavComponent,
    children: [
      { path: "inicio", component: HomeComponent },
      { path: "aluno", component: AlunoComponent },
      { path: "aluno/crud", component: AlunoCrudComponent },
      { path: "instrutor", component: InstrutorComponent },
      { path: "instrutor/crud", component: InstrutorCrudComponent },
      { path: "**", pathMatch: "full", redirectTo: "inicio" },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
