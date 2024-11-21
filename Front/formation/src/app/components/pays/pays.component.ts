import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
//import { paysComponent } from './components/pays/pays.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AjoutPaysComponent } from '../ajout-pays/ajout-pays.component';
import { PaysService } from 'src/app/services/pays.service';

//import {MatDialigRef}
@Component({
  selector: 'app-pays',
  templateUrl: './pays.component.html',
  styleUrls: ['./pays.component.css']
})
export class PaysComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nom', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private dialog: MatDialog,
    private paysService: PaysService) { }

  ngOnInit(): void {
    this.getAllPayss();

  }

  openPays() {
    this.dialog.open(AjoutPaysComponent, {
      width: '30%'
    }).afterClosed().subscribe(val => {
      if (val === 'save') {
        this.getAllPayss();
      }
    })
  }

  editPays(row: any) {
    this.dialog.open(AjoutPaysComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(val => {
      if (val === 'update') {
        this.getAllPayss();
      }
    })
  }
  getAllPayss() {
    this.paysService.getAll().subscribe({
      next: (res) => {
        // console.log(res);
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        alert("Erreur dans la récupération des données");
      }
    })
  }

  deletePays(id: number) {
    this.paysService.delete(id).subscribe({
      next: (res) => {
        alert("La pays est supprimé avec succès");
        this.getAllPayss();
      },
      error: () => {
        alert("Erreur dans la suppression de pays")
      }
    })

  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
