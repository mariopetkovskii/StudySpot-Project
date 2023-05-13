import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LectureDialogComponentComponent } from './lecture-dialog-component.component';

describe('LectureDialogComponentComponent', () => {
  let component: LectureDialogComponentComponent;
  let fixture: ComponentFixture<LectureDialogComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LectureDialogComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LectureDialogComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
