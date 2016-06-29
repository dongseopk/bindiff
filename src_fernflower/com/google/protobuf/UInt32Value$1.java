package com.google.protobuf;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UInt32Value;

final class UInt32Value$1 extends AbstractParser {
   public UInt32Value parsePartialFrom(CodedInputStream var1, ExtensionRegistryLite var2) {
      try {
         return new UInt32Value(var1, var2, (UInt32Value$1)null);
      } catch (RuntimeException var4) {
         if(var4.getCause() instanceof InvalidProtocolBufferException) {
            throw (InvalidProtocolBufferException)var4.getCause();
         } else {
            throw var4;
         }
      }
   }
}
