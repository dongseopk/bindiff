package com.google.protos.BinExport;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protos.BinExport.BinExport$1;
import com.google.protos.BinExport.BinExport$Flowgraph$Vertex$Instruction$Comment;

final class BinExport$Flowgraph$Vertex$Instruction$Comment$1 extends AbstractParser {
   public BinExport$Flowgraph$Vertex$Instruction$Comment parsePartialFrom(CodedInputStream var1, ExtensionRegistryLite var2) {
      try {
         return new BinExport$Flowgraph$Vertex$Instruction$Comment(var1, var2, (BinExport$1)null);
      } catch (RuntimeException var4) {
         if(var4.getCause() instanceof InvalidProtocolBufferException) {
            throw (InvalidProtocolBufferException)var4.getCause();
         } else {
            throw var4;
         }
      }
   }
}
