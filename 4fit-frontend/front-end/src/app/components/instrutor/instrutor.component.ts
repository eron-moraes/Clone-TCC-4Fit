import { Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatDialog } from "@angular/material/dialog";
import { MatTableDataSource } from "@angular/material/table";
import { Router } from "@angular/router";
import { Instrutor } from "src/app/models/instrutor";
import { InstrutorService } from "src/app/services/instrutor.service";
import { InstrutorCrudComponent } from "./instrutor-crud/instrutor-crud.component";

@Component({
  selector: "app-instrutor",
  templateUrl: "./instrutor.component.html",
  styleUrls: ["./instrutor.component.scss"],
})
export class InstrutorComponent implements OnInit {
  ELEMENT_DATA: Instrutor[] = [];

  displayedColumns: string[] = [
    "id",
    "nome",
    "cpf",
    "email",
    "telefone",
    "dataNascimento",
    "acoes",
  ];
  dataSource = new MatTableDataSource<Instrutor>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(
    private service: InstrutorService,
    private route: Router,
    private dialog: MatDialog
  ) {}
  openDialog(element: Instrutor, operacao: string = ""): void {
    const dialogRef = this.dialog.open(InstrutorCrudComponent, {
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
      this.dataSource = new MatTableDataSource<Instrutor>(resposta);
      this.dataSource.paginator = this.paginator;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  crudInstrutor(id: number = null, operacao: string) {
    this.route.navigate(["/instrutor/crud", { id: id, operacao: operacao }]);
  }
}
