import { Component, OnInit, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { Router } from "@angular/router";
import { Aluno } from "src/app/models/aluno";
import { AlunoService } from "src/app/services/aluno.service";

import { AlunoCrudComponent } from "./aluno-crud/aluno-crud.component";

@Component({
  selector: "app-aluno",
  templateUrl: "./aluno.component.html",
  styleUrls: ["./aluno.component.scss"],
})
export class AlunoComponent implements OnInit {
  ELEMENT_DATA: Aluno[] = [];

  displayedColumns: string[] = [
    "id",
    "nome",
    "cpf",
    "email",
    "telefone",
    "dataNascimento",
    "acoes",
  ];
  dataSource = new MatTableDataSource<Aluno>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(
    private service: AlunoService,
    private route: Router,
    private dialog: MatDialog
  ) {}
  openDialog(element: Aluno, operacao: string = ""): void {
    const dialogRef = this.dialog.open(AlunoCrudComponent, {
      data: { element: element, operacao: operacao },
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.findAll();
    });
  }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe((resposta) => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<Aluno>(resposta);
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  crudAluno(id: number = null, operacao: string) {
    this.route.navigate(["/aluno/crud", { id: id, operacao: operacao }]);
  }
}
