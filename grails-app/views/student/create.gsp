<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:set var="entityNivel" value="${"hola"}" />
        <a href="#create-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-student" class="content scaffold-create" role="main">
            <h1>Registro de estudiantes</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.student}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.student}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.student}" method="POST">
                <fieldset class="form">
                <div class="fieldcontain required">
                    <label for="nombre">Nombre Completo
                    <span class="required-indicator">*</span>   
                    </label>
                    <g:field type="text" name="nombre" required="" value="${student?.nombre}" />
                </div>
                <div class="fieldcontain required">
                    <label for="correo">Correo
                    <span class="required-indicator">*</span>   
                    </label>
                    <g:field type="email" name="correo" required=""
                pattern="^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.([a-zA-Z]{2,4})+"
                title="Ingrese un formato de correo correcto" value="${student?.correo}" />
                </div>
                <div class="fieldcontain required">
                 <label for="matricula">Matricula
                    <span class="required-indicator">*</span>   
                    </label>
                    <g:field type="text" name="matricula"  required="" pattern="[0-9]{9}"
                title="9 dígitos requeridos" value="${student?.matricula}"/>
                </div>
                <div class="fieldcontain required">
                 <label for="semestre">Semestre
                    <span class="required-indicator">*</span>   
                    </label>
                    <g:field type="number" name="semestre" required="" min="1" max="10"
                title="Dígitos numérico requerido" value="${student?.semestre}"/>
                </div>
                <div class="fieldcontain required">
                 <label for="nivel">Nivel
                    <span class="required-indicator">*</span>   
                    </label>
                    <g:select id="nivel" name='nivel' value="${student?.nivel}"
                        from='${['Licenciatura','Maestría','Doctorado']}'
                        optionValue='value'
                        onchange="changeCourse()"></g:select>
                
                </div>
                <div class="fieldcontain required">
                 <label for="curso">Curso
                    <span class="required-indicator">*</span>   
                    </label>
                    <g:select id="curso" name='curso' value="${student?.curso}"
                        from='${['Enfermeria','Software','Arquitectura']}'
                        optionValue='value'></g:select>
                </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
        <g:javascript>
         const licArray = ['Enfermería', 'Software', 'Arquitectura']
        const masArray = ['Fiscal', 'Educación']
        const docArray = ['Comunicación', 'Gastronomía']

        const changeCourse = () =>{
            let valueN = $('#nivel').val()
            let selects = $("#curso").empty()
            let arraySelect;
            if(valueN === 'Licenciatura') arraySelect = licArray
            else if(valueN === 'Maestría') arraySelect = masArray
            else if(valueN === 'Doctorado') arraySelect = docArray
            $.each(arraySelect, (key,value) => {   
                $(selects)
                    .append($("<option></option>")
                    .attr("value", value)
                    .text(value)); 
            });
        }
        </g:javascript>
    </body>
</html>
