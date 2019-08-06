package ru.skillbox.socialnetwork.utils;

import java.util.function.Predicate;

public interface PredicateOpt {

   static <T> Predicate<T> not(Predicate<T> t) {
      return t.negate();
   }
}
