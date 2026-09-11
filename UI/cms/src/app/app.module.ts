import { APP_INITIALIZER, ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OpenAppModule } from './open-app/open-app.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { StudentLoginInterceptor } from './interceptors/student-login.interceptor';
import { AppInitService } from './app_initializer/app-init.service';
import { GlobalErrorHandlerService } from './error_handler/global-error-handler.service';

function initFactory(initService: AppInitService) {
  return ()=> initService.init(); 
}

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule,
    OpenAppModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: StudentLoginInterceptor,
      multi: true,
    }, 
    {
      provide: APP_INITIALIZER,
      useFactory: initFactory,
      deps: [AppInitService],
      multi: true
    },
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandlerService
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
