import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutFormateurComponent } from './ajout-formateur.component';

describe('AjoutFormateurComponent', () => {
  let component: AjoutFormateurComponent;
  let fixture: ComponentFixture<AjoutFormateurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjoutFormateurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjoutFormateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
