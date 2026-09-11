1. ng new cms  -> New Application
2. ng serve  -> start application 
3. ng build -c production or ng build --prod   -> build for prod

4. ng g m openApp --routing --module=app  -> generate a module openApp with its own routing and     import in app module (main module) No lazy loading 

5. ng g m admin --route=admin --module=app -> generate a module admin with its own routing and add in app (main) routing as lazy loading 

6. ng g m student --route=student --module=app -> generate a module student with its own routing and add in app (main) routing as lazy loading 

7. ng g m professor --routing -> generate a module professor with its own routing but does not import or registered in app 

8. ng add ngx-bootstrap - Add bootstrap in angular 13

9. cd src/app/open-app
   mkdir components
   cd components 
   ng g c <component-name> ->
        Create a home folder inside your openApp module directory.
        Generate the component files (home.component.ts, home.component.html, etc.) inside that home folder.
        Automatically import and declare HomeComponent inside your openApp.module.ts file.

10. ng g s <service-name>

11. ng g interceptor studentLogin -> Generate Interceptor

12. ng g g student -> generate guard 

13. ng g d fontAdmin -> generate directives