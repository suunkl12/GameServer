// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: my.proto

package gameserver;

public final class HotMessage {
  private HotMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface PackageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:gameserver.Package)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    int getId();

    /**
     * <code>string _msg = 2;</code>
     * @return The msg.
     */
    java.lang.String getMsg();
    /**
     * <code>string _msg = 2;</code>
     * @return The bytes for msg.
     */
    com.google.protobuf.ByteString
        getMsgBytes();
  }
  /**
   * Protobuf type {@code gameserver.Package}
   */
  public static final class Package extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:gameserver.Package)
      PackageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Package.newBuilder() to construct.
    private Package(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Package() {
      Msg_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Package();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Package(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              id_ = input.readInt32();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              Msg_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return gameserver.HotMessage.internal_static_gameserver_Package_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return gameserver.HotMessage.internal_static_gameserver_Package_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              gameserver.HotMessage.Package.class, gameserver.HotMessage.Package.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }

    public static final int _MSG_FIELD_NUMBER = 2;
    private volatile java.lang.Object Msg_;
    /**
     * <code>string _msg = 2;</code>
     * @return The msg.
     */
    @java.lang.Override
    public java.lang.String getMsg() {
      java.lang.Object ref = Msg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        Msg_ = s;
        return s;
      }
    }
    /**
     * <code>string _msg = 2;</code>
     * @return The bytes for msg.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = Msg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        Msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (id_ != 0) {
        output.writeInt32(1, id_);
      }
      if (!getMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, Msg_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (id_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, id_);
      }
      if (!getMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, Msg_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof gameserver.HotMessage.Package)) {
        return super.equals(obj);
      }
      gameserver.HotMessage.Package other = (gameserver.HotMessage.Package) obj;

      if (getId()
          != other.getId()) return false;
      if (!getMsg()
          .equals(other.getMsg())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
      hash = (37 * hash) + _MSG_FIELD_NUMBER;
      hash = (53 * hash) + getMsg().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static gameserver.HotMessage.Package parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static gameserver.HotMessage.Package parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static gameserver.HotMessage.Package parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static gameserver.HotMessage.Package parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static gameserver.HotMessage.Package parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static gameserver.HotMessage.Package parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static gameserver.HotMessage.Package parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static gameserver.HotMessage.Package parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static gameserver.HotMessage.Package parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static gameserver.HotMessage.Package parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static gameserver.HotMessage.Package parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static gameserver.HotMessage.Package parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(gameserver.HotMessage.Package prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code gameserver.Package}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:gameserver.Package)
        gameserver.HotMessage.PackageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return gameserver.HotMessage.internal_static_gameserver_Package_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return gameserver.HotMessage.internal_static_gameserver_Package_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                gameserver.HotMessage.Package.class, gameserver.HotMessage.Package.Builder.class);
      }

      // Construct using gameserver.HotMessage.Package.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        id_ = 0;

        Msg_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return gameserver.HotMessage.internal_static_gameserver_Package_descriptor;
      }

      @java.lang.Override
      public gameserver.HotMessage.Package getDefaultInstanceForType() {
        return gameserver.HotMessage.Package.getDefaultInstance();
      }

      @java.lang.Override
      public gameserver.HotMessage.Package build() {
        gameserver.HotMessage.Package result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public gameserver.HotMessage.Package buildPartial() {
        gameserver.HotMessage.Package result = new gameserver.HotMessage.Package(this);
        result.id_ = id_;
        result.Msg_ = Msg_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof gameserver.HotMessage.Package) {
          return mergeFrom((gameserver.HotMessage.Package)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(gameserver.HotMessage.Package other) {
        if (other == gameserver.HotMessage.Package.getDefaultInstance()) return this;
        if (other.getId() != 0) {
          setId(other.getId());
        }
        if (!other.getMsg().isEmpty()) {
          Msg_ = other.Msg_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        gameserver.HotMessage.Package parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (gameserver.HotMessage.Package) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int id_ ;
      /**
       * <code>int32 id = 1;</code>
       * @return The id.
       */
      @java.lang.Override
      public int getId() {
        return id_;
      }
      /**
       * <code>int32 id = 1;</code>
       * @param value The id to set.
       * @return This builder for chaining.
       */
      public Builder setId(int value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 id = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearId() {
        
        id_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object Msg_ = "";
      /**
       * <code>string _msg = 2;</code>
       * @return The msg.
       */
      public java.lang.String getMsg() {
        java.lang.Object ref = Msg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          Msg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string _msg = 2;</code>
       * @return The bytes for msg.
       */
      public com.google.protobuf.ByteString
          getMsgBytes() {
        java.lang.Object ref = Msg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          Msg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string _msg = 2;</code>
       * @param value The msg to set.
       * @return This builder for chaining.
       */
      public Builder setMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        Msg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string _msg = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMsg() {
        
        Msg_ = getDefaultInstance().getMsg();
        onChanged();
        return this;
      }
      /**
       * <code>string _msg = 2;</code>
       * @param value The bytes for msg to set.
       * @return This builder for chaining.
       */
      public Builder setMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        Msg_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:gameserver.Package)
    }

    // @@protoc_insertion_point(class_scope:gameserver.Package)
    private static final gameserver.HotMessage.Package DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new gameserver.HotMessage.Package();
    }

    public static gameserver.HotMessage.Package getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Package>
        PARSER = new com.google.protobuf.AbstractParser<Package>() {
      @java.lang.Override
      public Package parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Package(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Package> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Package> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public gameserver.HotMessage.Package getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_gameserver_Package_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_gameserver_Package_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\010my.proto\022\ngameserver\"#\n\007Package\022\n\n\002id\030" +
      "\001 \001(\005\022\014\n\004_msg\030\002 \001(\tB\014B\nHotMessageb\006proto" +
      "3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_gameserver_Package_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_gameserver_Package_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_gameserver_Package_descriptor,
        new java.lang.String[] { "Id", "Msg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
