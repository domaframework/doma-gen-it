/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package example.config;

import java.util.Arrays;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.seasar.doma.jdbc.tx.TransactionManager;

/** @author nakamura-to */
public class Sandbox implements TestRule {

  private Container container;

  public Sandbox(Container container) {
    this.container = container;
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return new Statement() {

      @Override
      public void evaluate() throws Throwable {
        if (meetsPrecondition(description)) {
          executeInTransaction(base);
        } else {
          throw new AssumptionViolatedException("skip");
        }
      }
    };
  }

  protected boolean meetsPrecondition(Description description) {
    Dbms dbms = container.get(c -> c.getDbms());
    Run runAtClass = description.getTestClass().getAnnotation(Run.class);
    if (isRunnable(runAtClass, dbms)) {
      Run runAtMethod = description.getAnnotation(Run.class);
      return isRunnable(runAtMethod, dbms);
    }
    return false;
  }

  protected boolean isRunnable(Run run, Dbms dbms) {
    return run == null
        || Arrays.asList(run.onlyIf()).contains(dbms)
        || !Arrays.asList(run.unless()).contains(dbms);
  }

  protected void executeInTransaction(Statement statement) throws Throwable {
    TransactionManager tm = container.get(c -> c.getTransactionManager());
    tm.required(
        () -> {
          try {
            statement.evaluate();
          } catch (Throwable th) {
            throw new RuntimeException(th);
          }
          tm.setRollbackOnly();
        });
  }
}
