import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AjoutOrganismeComponent } from '../ajout-organisme/ajout-organisme.component';
import { AjoutFormateurComponent } from '../ajout-formateur/ajout-formateur.component';
import { FormateurService } from 'src/app/services/formateur.service';

@Component({
  selector: 'app-formateur',
  templateUrl: './formateur.component.html',
  styleUrls: ['./formateur.component.css']
})
export class FormateurComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nom', 'prenom', 'email', 'telephone', 'type', 'organisme', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dialog: MatDialog,
    private formateurService: FormateurService) { }

  ngOnInit(): void {
    this.getAllFormateurs();
  }

  openFormateur() {
    this.dialog.open(AjoutFormateurComponent, {
      width: '30%'
    }).afterClosed().subscribe(val => {
      if (val === 'save') {
        this.getAllFormateurs();
      }
    })
  }

  editFormateur(row: any) {
    console.log('id org', row.organisme.id)
    this.dialog.open(AjoutFormateurComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(val => {
      if (val === 'update') {
        this.getAllFormateurs();
      }
    })
  }
  getAllFormateurs() {
    this.formateurService.getAll().subscribe({
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

  deleteFormateur(id: number) {
    this.formateurService.delete(id).subscribe({
      next: (res) => {
        alert("Le Formateur est supprimé avec succès");
        this.getAllFormateurs();
      },
      error: () => {
        alert("Erreur dans la suppression du formateur")
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
