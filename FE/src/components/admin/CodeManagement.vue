<template>
  <div class="min-h-screen bg-gray-50 py-8 px-4 sm:px-6 lg:px-8">
    <div class="max-w-7xl mx-auto">
      <div class="mb-8">
        <h1 class="text-2xl font-bold text-gray-900">코드 관리</h1>
        <p class="mt-2 text-sm text-gray-600">시스템에서 사용되는 코드를 관리합니다.</p>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div class="bg-white rounded-lg shadow">
          <div class="p-4 border-b border-gray-200 flex justify-between items-center">
            <h2 class="text-lg font-medium text-gray-900">코드 그룹</h2>
            <div class="flex space-x-2">
              <button @click="openAddGroupModal" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700">
                <PlusIcon class="h-4 w-4 mr-1" />
                추가
              </button>
              <button @click="deleteSelectedGroup" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700">
                <TrashIcon class="h-4 w-4 mr-1" />
                삭제
              </button>
            </div>
          </div>
          <div class="p-4">
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr>
                  <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">순번</th>
                  <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">그룹 ID</th>
                  <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">그룹명</th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="(group, index) in codeGroups" 
                    :key="group.groupId" 
                    @click="selectGroup(group)"
                    class="hover:bg-gray-50 cursor-pointer"
                    :class="{'bg-indigo-50': selectedGroup?.groupId === group.groupId}">
                  <td class="px-4 py-3 whitespace-nowrap text-sm text-gray-500">{{ index + 1 }}</td>
                  <td class="px-4 py-3 whitespace-nowrap text-sm text-gray-900">{{ group.groupId }}</td>
                  <td class="px-4 py-3 whitespace-nowrap text-sm font-medium text-gray-900">{{ group.groupName }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <div class="lg:col-span-2 bg-white rounded-lg shadow">
          <div class="p-4 border-b border-gray-200 flex justify-between items-center">
            <h2 class="text-lg font-medium text-gray-900">코드 상세</h2>
            <div class="flex space-x-2">
              <button @click="openAddCodeModal" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700">
                <PlusIcon class="h-4 w-4 mr-1" />
                추가
              </button>
              <button @click="deleteSelectedCode" class="inline-flex items-center px-3 py-1.5 border border-transparent text-sm font-medium rounded-md text-white bg-red-600 hover:bg-red-700">
                <TrashIcon class="h-4 w-4 mr-1" />
                삭제
              </button>
            </div>
          </div>
          <div class="p-4">
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">순서</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">코드명</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">코드ID</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">코드설명</th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="code in codes" :key="code.codeId" 
                    @click="selectCode(code)"
                    class="hover:bg-gray-50 cursor-pointer"
                    :class="{'bg-indigo-50': selectedCode?.codeId === code.codeId}">
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ code.sortOrder }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{{ code.codeName }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ code.codeId }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ code.description }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div v-if="showGroupModal" class="fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center">
    <div class="bg-white rounded-lg p-6 w-full max-w-md">
      <h3 class="text-lg font-medium text-gray-900 mb-4">코드 그룹 추가</h3>
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700">그룹 ID</label>
          <input v-model="newGroup.groupId" 
                 type="text" 
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500" placeholder="그룹 ID를 입력해주세요.">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">그룹명</label>
          <input v-model="newGroup.groupName" 
                 type="text" 
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500" placeholder="그룹명을 입력해주세요.">
        </div>
        <div class="flex justify-end space-x-3">
          <button @click="closeGroupModal" class="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200">
            취소
          </button>
          <button @click="addGroup" class="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700">
            추가
          </button>
        </div>
      </div>
    </div>
  </div>

  <div v-if="showCodeModal" class="fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center">
    <div class="bg-white rounded-lg p-6 w-full max-w-md">
      <h3 class="text-lg font-medium text-gray-900 mb-4">코드 추가</h3>
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium text-gray-700">그룹 ID</label>
          <input v-model="selectedGroup.groupId" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500" readonly>
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">코드 ID</label>
          <input v-model="newCode.codeId" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500" placeholder="코드 ID를 입력해주세요.">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">코드명</label>
          <input v-model="newCode.codeName" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500" placeholder="코드명을 입력해주세요.">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">설명</label>
          <input v-model="newCode.description" type="text" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500" placeholder="설명을 입력해주세요.">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">순서</label>
          <input v-model="newCode.sortOrder" type="number" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div class="flex justify-end space-x-3">
          <button @click="closeCodeModal" class="px-4 py-2 text-sm font-medium text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200">
            취소
          </button>
          <button @click="addCode" class="px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700">
            추가
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {PlusIcon, TrashIcon} from '@heroicons/vue/24/outline'
import {useToast} from "vue-toastification";

const codeGroups = ref([])
const codes = ref([])
const selectedGroup = ref(null)
const selectedCode = ref(null)
const toast = useToast();

onMounted(async () => {
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/admin/code-management/code-group`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include'
    });
    const data = await response.json();
    data.forEach((item) => codeGroups.value.push({...item, codes: []}))
  } catch (e) {
    toast.error('내 정보 조회에 실패했습니다.');
  }
});

const selectGroup = async (group) => {
  selectedGroup.value = group
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/admin/code-management/code?groupId=${group.groupId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include'
    });
    group.codes = await response.json();
    codes.value = group.codes || []
  } catch (e) {
    console.log(e)
    toast.error('해당 그룹의 코드 조회에 실패했습니다.');
  }
}

const selectCode = (code) => {
  selectedCode.value = code
}

const showGroupModal = ref(false)
const showCodeModal = ref(false)
const newGroup = ref({
  groupId: '',
  groupName: ''
})
const newCode = ref({
  groupId: '',
  codeId: '',
  codeName: '',
  description: '',
  sortOrder: 1
})

const openAddGroupModal = () => {
  showGroupModal.value = true
  newGroup.value = { groupId: '', groupName: '' }
}

const closeGroupModal = () => {
  showGroupModal.value = false
}

const addGroup = async () => {
  if (!newGroup.value.groupId || !newGroup.value.groupName) {
    toast.error("그룹 ID와 그룹 이름을 모두 입력하세요")
    return;
  }
  try {
    await fetch(`${import.meta.env.VITE_API_URL}/api/v1/admin/code-management/code-group`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        groupId: newGroup.value.groupId,
        groupName: newGroup.value.groupName
      }),
      credentials: 'include'
    });
    codeGroups.value.push({
      ...newGroup.value,
      codes: []
    });
    closeGroupModal();
  } catch (e) {
    toast.error('그룹 추가에 실패했습니다.');
  }
}

const openAddCodeModal = () => {
  if (!selectedGroup.value) {
    toast.error('추가하고자 하는 코드 그룹을 선택해주세요');
    return;
  }
  showCodeModal.value = true
  newCode.value = {
    codeId: '',
    codeName: '',
    description: '',
    sortOrder: codes.value.length + 1
  }
}

const closeCodeModal = () => {
  showCodeModal.value = false
}

const addCode = async () => {
  if (!selectedGroup.value) return;
  if (!selectedGroup.value.groupId || !newCode.value.codeId || !newCode.value.codeName || !newCode.value.sortOrder) {
    toast.error("필드 값을 입력해주세요.");
    return;
  }
  try {
    console.log(newCode.value.sortOrder);
    await fetch(`${import.meta.env.VITE_API_URL}/api/v1/admin/code-management/code`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        codeId: newCode.value.codeId,
        codeName: newCode.value.codeName,
        description: newCode.value.description,
        groupId: selectedGroup.value.groupId,
        sortOrder: newCode.value.sortOrder
      }),
      credentials: 'include'
    });
    const groupIndex = codeGroups.value.findIndex(g => g.groupId === selectedGroup.value.groupId)
    if (groupIndex !== -1) {
      if (!codeGroups.value[groupIndex].codes) {
        codeGroups.value[groupIndex].codes = []
      }
      codeGroups.value[groupIndex].codes.push({ ...newCode.value })
      codes.value = codeGroups.value[groupIndex].codes
    }
    toast.success('코드를 추가했습니다.');
    closeCodeModal();
  } catch (e) {
    toast.error('그룹 추가에 실패했습니다.');
  }
}

const deleteSelectedGroup = () => {
  // TODO: 선택된 그룹 삭제 구현
}

const deleteSelectedCode = () => {
  // TODO: 선택된 코드 삭제 구현
}
</script>