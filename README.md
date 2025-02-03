## Service

Simple test project with Spring JPA + OpenFeign QueryDSL to reproduce an issue with @MappedSuperclass and @Querysupertype on an abstract BaseEntity (https://github.com/OpenFeign/querydsl/issues/862)

NOTE - this project doesnt do much useful, except house the code that fails during KSP processing. When using BaseEntity in the dependent project, Querydsl throws a NullPointerException during KSP processing.
