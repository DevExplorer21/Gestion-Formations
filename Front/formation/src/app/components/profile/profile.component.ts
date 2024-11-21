import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
//import { DomainComponent } from './components/domain/domain.component';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { AjoutProfileComponent } from '../ajout-profile/ajout-profile.component';
import { ProfileService } from 'src/app/services/profile.service';

//import {MatDialigRef}
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
 
  displayedColumns: string[] = ['id', 'libelle', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private dialog: MatDialog,
    private profileService: ProfileService) { }
 
  ngOnInit(): void {
    this.getAllProfiles();

  }
   
    openProfile() {
      this.dialog.open(AjoutProfileComponent, {
        width:'30%'
      }).afterClosed().subscribe(val=>{
        if(val==='save'){
          this.getAllProfiles();
        }
      })
    }
  
    editProfile(row:any){
      this.dialog.open(AjoutProfileComponent,{
        width:'30%',
        data:row
      }).afterClosed().subscribe(val=>{
        if(val==='update'){
          this.getAllProfiles();
        }
    })
  }
    getAllProfiles(){
       this.profileService.getAll().subscribe({
         next:(res)=>{
         // console.log(res);
         this.dataSource=new MatTableDataSource(res);
         this.dataSource.paginator=this.paginator;
         this.dataSource.sort=this.sort;
  
         },
         error:(err)=>{
          alert("Erreur dans la récupération des données");
         }
       })
    }
  
    deleteProfile(id:number){
      this.profileService.delete(id).subscribe({
        next:(res)=>{
          alert("Le profile est supprimé avec succès");
          this.getAllProfiles();
        },
        error:()=>{
          alert("Erreur dans la suppression de profile")
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
