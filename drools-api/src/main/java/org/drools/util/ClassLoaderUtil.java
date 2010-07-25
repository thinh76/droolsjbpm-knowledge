/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.util;

public class ClassLoaderUtil {
    public static CompositeClassLoader getClassLoader(final ClassLoader classLoader,
                                                      final Class< ? > cls,
                                                      final boolean enableCache) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader currentClassLoader = (cls != null) ? cls.getClassLoader() : ClassLoaderUtil.class.getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        CompositeClassLoader cl = new CompositeClassLoader( null );

        if ( classLoader != null ) {
            // the user specified classloader
            cl.addClassLoader( classLoader );
        }

        if ( currentClassLoader != null ) {
            // the current classloader, typically from a drools-core or drools-compiler class
            cl.addClassLoader( currentClassLoader );
        }

        if ( contextClassLoader != null ) {
            // context classloader
            cl.addClassLoader( contextClassLoader );
        }

        if ( systemClassLoader != null ) {
            // system classloader
            cl.addClassLoader( systemClassLoader );
        }

        cl.setCachingEnabled( enableCache );

        return cl;
    }
}
