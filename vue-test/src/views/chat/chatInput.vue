<script setup lang="ts">
import { onBeforeUnmount, ref, watch } from "vue";
import {
  ExposeParam,
  FocusOption,
  MdEditor,
  ToolbarNames,
  MdPreview,
} from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import { Emoji } from "@vavt/v3-extension";
import "@vavt/v3-extension/lib/asset/style.css";
import { ElMessage } from "element-plus";

const emits = defineEmits<{ sendMessage: [] }>();
const editorRef = ref<ExposeParam>();

const model = defineModel({ type: String });

const toolbars: ToolbarNames[] = [0, "revoke", "next", "preview"];
// const footers = [];
const option: FocusOption | undefined = "start";
const focus = () => {
  editorRef.value?.focus(option);
  // console.log("focus");
};

defineExpose({ focus });

const sendMessage = () => {
  if (model.value != "") {
    emits("sendMessage");
  } else {
    ElMessage.warning("发送内容不能为空");
  }
};
</script>

<template>
  <div class="chat_input">
    <div class="chat_input_text">
      <MdEditor
        style=""
        v-model="model"
        :autoFocus="true"
        :toolbars="toolbars"
        :footers="[]"
        :preview="false"
        ref="editorRef"
      >
        <template #defToolbars>
          <Emoji />
        </template>
        <!-- footer -->
        <template #defFooters>
          <div>
            <el-button>发送</el-button>
          </div>
        </template>
      </MdEditor>
    </div>

    <div class="chat_input_submit">
      <el-tooltip
        class="box-item"
        effect="light"
        content="Alt+Enter"
        placement="top-start"
      >
        <el-button @click="sendMessage" type="primary">发送</el-button>
      </el-tooltip>
    </div>
  </div>
</template>

<style scoped lang="scss">
:deep(.md-editor) {
  --md-bk-color: #ffffffe8;
}
.chat_input {
  @apply h-full w-full bg-[#ffffffe8];
  .chat_input_text {
  }
  .chat_input_submit {
    @apply flex justify-end px-2 py-2;
  }
}

.md-editor {
  @apply h-60;
}
</style>
