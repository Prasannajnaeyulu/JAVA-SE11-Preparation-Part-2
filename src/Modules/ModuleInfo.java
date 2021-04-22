package Modules;

public class ModuleInfo {
//
//            Question 1:
//    Which of the following are NOT true about modules in Java 11?
//
//            * A.  You can run an application on Java 11 using a combination of both modular jars and non-modular jars.
//
//            * B.   Classes loaded from the class path (in jars or individiually) get added to a module in the JVM called the unnamed module.
//
//            * C.  A package can be in both the unnamed module and a named module, but the package in the unnamed module is ignored.
//
//            * D.  The unnamed module can read every other module.
//
//            * E.   An automatic module is the same as an unnamed module.
//
//            * F.   Every package in an automatic module is considered to be exported.
//
//* G.  All of the statements above are correct

    // Answer: E This statement is not valid. Classes in a jar on the class path are loaded into the unnamed module.
    // A jar that is loaded from the module path, which does not contain a module descriptor is loaded as an automatic module,
    // and given a default name based on the jar name. All of it’s packages are considered exported.
}
