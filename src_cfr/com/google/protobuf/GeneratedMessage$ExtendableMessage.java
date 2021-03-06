/*
 * Decompiled with CFR 0_115.
 */
package com.google.protobuf;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Descriptors$Descriptor;
import com.google.protobuf.Descriptors$FieldDescriptor;
import com.google.protobuf.Descriptors$FieldDescriptor$JavaType;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Extension;
import com.google.protobuf.ExtensionLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.FieldSet$FieldDescriptorLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage$1;
import com.google.protobuf.GeneratedMessage$Builder;
import com.google.protobuf.GeneratedMessage$ExtendableBuilder;
import com.google.protobuf.GeneratedMessage$ExtendableMessage$ExtensionWriter;
import com.google.protobuf.GeneratedMessage$ExtendableMessageOrBuilder;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.MessageReflection$ExtensionAdapter;
import com.google.protobuf.UnknownFieldSet$Builder;
import java.util.Collections;
import java.util.Map;

public abstract class GeneratedMessage$ExtendableMessage
extends GeneratedMessage
implements GeneratedMessage$ExtendableMessageOrBuilder {
    private final FieldSet extensions;

    protected GeneratedMessage$ExtendableMessage() {
        this.extensions = FieldSet.newFieldSet();
    }

    protected GeneratedMessage$ExtendableMessage(GeneratedMessage$ExtendableBuilder generatedMessage$ExtendableBuilder) {
        super(generatedMessage$ExtendableBuilder);
        this.extensions = GeneratedMessage$ExtendableBuilder.access$400(generatedMessage$ExtendableBuilder);
    }

    private void verifyExtensionContainingType(Extension extension) {
        if (extension.getDescriptor().getContainingType() == this.getDescriptorForType()) return;
        String string = String.valueOf(String.valueOf(extension.getDescriptor().getContainingType().getFullName()));
        String string2 = String.valueOf(String.valueOf(this.getDescriptorForType().getFullName()));
        throw new IllegalArgumentException(new StringBuilder(62 + string.length() + string2.length()).append("Extension is for type \"").append(string).append("\" which does not match message type \"").append(string2).append("\".").toString());
    }

    @Override
    public final boolean hasExtension(ExtensionLite extensionLite) {
        Extension extension = GeneratedMessage.access$500(extensionLite);
        this.verifyExtensionContainingType(extension);
        return this.extensions.hasField(extension.getDescriptor());
    }

    @Override
    public final int getExtensionCount(ExtensionLite extensionLite) {
        Extension extension = GeneratedMessage.access$500(extensionLite);
        this.verifyExtensionContainingType(extension);
        Descriptors$FieldDescriptor descriptors$FieldDescriptor = extension.getDescriptor();
        return this.extensions.getRepeatedFieldCount(descriptors$FieldDescriptor);
    }

    @Override
    public final Object getExtension(ExtensionLite extensionLite) {
        Extension extension = GeneratedMessage.access$500(extensionLite);
        this.verifyExtensionContainingType(extension);
        Descriptors$FieldDescriptor descriptors$FieldDescriptor = extension.getDescriptor();
        Object object = this.extensions.getField(descriptors$FieldDescriptor);
        if (object != null) return extension.fromReflectionType(object);
        if (descriptors$FieldDescriptor.isRepeated()) {
            return Collections.emptyList();
        }
        if (descriptors$FieldDescriptor.getJavaType() != Descriptors$FieldDescriptor$JavaType.MESSAGE) return extension.fromReflectionType(descriptors$FieldDescriptor.getDefaultValue());
        return extension.getMessageDefaultInstance();
    }

    @Override
    public final Object getExtension(ExtensionLite extensionLite, int n2) {
        Extension extension = GeneratedMessage.access$500(extensionLite);
        this.verifyExtensionContainingType(extension);
        Descriptors$FieldDescriptor descriptors$FieldDescriptor = extension.getDescriptor();
        return extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptors$FieldDescriptor, n2));
    }

    protected boolean extensionsAreInitialized() {
        return this.extensions.isInitialized();
    }

    @Override
    public boolean isInitialized() {
        if (!super.isInitialized()) return false;
        if (!this.extensionsAreInitialized()) return false;
        return true;
    }

    @Override
    protected boolean parseUnknownField(CodedInputStream codedInputStream, UnknownFieldSet$Builder unknownFieldSet$Builder, ExtensionRegistryLite extensionRegistryLite, int n2) {
        return MessageReflection.mergeFieldFrom(codedInputStream, unknownFieldSet$Builder, extensionRegistryLite, this.getDescriptorForType(), new MessageReflection$ExtensionAdapter(this.extensions), n2);
    }

    @Override
    protected void makeExtensionsImmutable() {
        this.extensions.makeImmutable();
    }

    protected GeneratedMessage$ExtendableMessage$ExtensionWriter newExtensionWriter() {
        return new GeneratedMessage$ExtendableMessage$ExtensionWriter(this, false, null);
    }

    protected GeneratedMessage$ExtendableMessage$ExtensionWriter newMessageSetExtensionWriter() {
        return new GeneratedMessage$ExtendableMessage$ExtensionWriter(this, true, null);
    }

    protected int extensionsSerializedSize() {
        return this.extensions.getSerializedSize();
    }

    protected int extensionsSerializedSizeAsMessageSet() {
        return this.extensions.getMessageSetSerializedSize();
    }

    protected Map getExtensionFields() {
        return this.extensions.getAllFields();
    }

    @Override
    public Map getAllFields() {
        Map map = GeneratedMessage.access$800(this, false);
        map.putAll(this.getExtensionFields());
        return Collections.unmodifiableMap(map);
    }

    @Override
    public Map getAllFieldsRaw() {
        Map map = GeneratedMessage.access$800(this, false);
        map.putAll(this.getExtensionFields());
        return Collections.unmodifiableMap(map);
    }

    @Override
    public boolean hasField(Descriptors$FieldDescriptor descriptors$FieldDescriptor) {
        if (!descriptors$FieldDescriptor.isExtension()) return super.hasField(descriptors$FieldDescriptor);
        this.verifyContainingType(descriptors$FieldDescriptor);
        return this.extensions.hasField(descriptors$FieldDescriptor);
    }

    @Override
    public Object getField(Descriptors$FieldDescriptor descriptors$FieldDescriptor) {
        if (!descriptors$FieldDescriptor.isExtension()) return super.getField(descriptors$FieldDescriptor);
        this.verifyContainingType(descriptors$FieldDescriptor);
        Object object = this.extensions.getField(descriptors$FieldDescriptor);
        if (object != null) return object;
        if (descriptors$FieldDescriptor.getJavaType() != Descriptors$FieldDescriptor$JavaType.MESSAGE) return descriptors$FieldDescriptor.getDefaultValue();
        return DynamicMessage.getDefaultInstance(descriptors$FieldDescriptor.getMessageType());
    }

    @Override
    public int getRepeatedFieldCount(Descriptors$FieldDescriptor descriptors$FieldDescriptor) {
        if (!descriptors$FieldDescriptor.isExtension()) return super.getRepeatedFieldCount(descriptors$FieldDescriptor);
        this.verifyContainingType(descriptors$FieldDescriptor);
        return this.extensions.getRepeatedFieldCount(descriptors$FieldDescriptor);
    }

    @Override
    public Object getRepeatedField(Descriptors$FieldDescriptor descriptors$FieldDescriptor, int n2) {
        if (!descriptors$FieldDescriptor.isExtension()) return super.getRepeatedField(descriptors$FieldDescriptor, n2);
        this.verifyContainingType(descriptors$FieldDescriptor);
        return this.extensions.getRepeatedField(descriptors$FieldDescriptor, n2);
    }

    private void verifyContainingType(Descriptors$FieldDescriptor descriptors$FieldDescriptor) {
        if (descriptors$FieldDescriptor.getContainingType() == this.getDescriptorForType()) return;
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }

    static /* synthetic */ FieldSet access$600(GeneratedMessage$ExtendableMessage generatedMessage$ExtendableMessage) {
        return generatedMessage$ExtendableMessage.extensions;
    }
}

